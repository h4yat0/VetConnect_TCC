package br.vetconnect.api.service.Email;



import br.vetconnect.api.form.AgendamentoEmail;
import br.vetconnect.api.service.Agendamento.AgendamentoService;
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

    @Autowired
    private AgendamentoService service;

    @Scheduled(cron = "0 00 05 * * ?", zone = "")
    public void enviarEmail() throws MessagingException {
        try{
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataDaquiDoisDias = LocalDate.now().plusDays(2);

            List<AgendamentoEmail> agendamentoEmails = service.criaNotificaoEmail(dataAtual, dataDaquiDoisDias);



            for(AgendamentoEmail entity : agendamentoEmails ){
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
