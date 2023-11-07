package br.vetconnect.api.service.Email;



import br.vetconnect.api.form.AgendamentoEmail;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
public class NotificacaoService {



    @Autowired
    private JavaMailSender emailSender;

    @PersistenceContext
    private EntityManager manager;

    @Scheduled(cron = "0 00 09 * * ?", zone = "")
    public void enviarEmail() throws MessagingException {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataDaquiDoisDias = LocalDate.now().plusDays(2);
        String sql = "SELECT cli.nome AS nomeCliente, ani.nome AS nomeAnimal, ser.nome AS nomeServico, uni.nome AS nomeUnidade,\n" +
                "age.hora_agendada AS horaAgendamento, uni.rua AS ruaUnidade, uni.bairro AS bairroUnidade, uni.contato AS contatoUnidade,uni.cidade AS cidadeUnidade, "+
                " cli.email AS emailCliente, age.data_agendada AS dataAgendamento " +
                "FROM agendamento AS age \n" +
                "LEFT JOIN cliente AS cli ON cli.id = age.id_cliente \n" +
                "LEFT JOIN animal AS ani ON ani.id = age.id_animal\n" +
                "LEFT JOIN servico AS ser ON ser.id = age.id_servico\n" +
                "LEFT JOIN unidade AS uni ON uni.id = age.id_unidade\n" +
                "where age.data_agendada = :dataAtual and age.cancelado = false and age.data_agendada = :dataDaquiDoisDias";

        Query query = manager.createNativeQuery(sql);
        query.setParameter("dataAtual", dataAtual);
        query.setParameter("dataDaquiDoisDias", dataDaquiDoisDias);

        List<AgendamentoEmail> agendamento = query.getResultList();



        for(AgendamentoEmail entity : agendamento ){
            var mensagem = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mensagem, true);

            helper.setTo(entity.getEmailCliente());
            helper.setSubject("Consulta");
            if(entity.getDataAgendamento().equals(dataAtual.toString())){
                helper.setText("Olá " +entity.getNomeCliente() + "Espero que esteja tudo bem. Gostaríamos de lembrá-lo de que você tem um " +
                        "agendamento, "+entity.getNomeServico()+" para o seu adorável animalzinho"+ entity.getNomeAnimal()+ "hoje, " + dataAtual +" " + entity.getHoraAgendamento() +
                        "Na unidade " + entity.getNomeUnidade() +
                        "\n localizada em:  " + entity.getRuaUnidade() + ", " + entity.getBairroUnidade() + ", " + entity.getCidadeUnidade() +". \n"+
                        "Para qualquer duvida entrar em contato no numero: " +entity.getContatoUnidade()
                );
            }else{
                helper.setText("Olá " +entity.getNomeCliente() + "Espero que esta mensagem o encontre bem. Gostaríamos de lembrá-lo de que você tem um " +
                        "agendamento, "+entity.getNomeServico()+" para o seu adorável animalzinho"+ entity.getNomeAnimal()+ "daqui dois dias, nas horas " + entity.getHoraAgendamento() +
                        "Na unidade " + entity.getNomeUnidade() +
                        "\n localizada em:  " + entity.getRuaUnidade() + ", " + entity.getBairroUnidade() + ", " + entity.getCidadeUnidade()
                );
            }


            emailSender.send(mensagem);


        }
    }

}
