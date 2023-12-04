package br.vetconnect.api.service.Email;



import br.vetconnect.api.form.AgendamentoEmail;
import br.vetconnect.api.service.Agendamento.AgendamentoService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    @Scheduled(cron = "0 50 19 * * ?", zone = "")
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
                    helper.setText("Olá " +entity.getNomeCliente() + ". Espero que esteja tudo bem. Gostaríamos de lembrá-lo de que você tem um " +
                            "agendamento, "+entity.getNomeServico()+" para o seu adorável animalzinho "+ entity.getNomeAnimal()+ " hoje, " + formataData(dataAtual.toString()) +" as " + entity.getHoraAgendamento() +
                            " Na unidade " + entity.getNomeUnidade() +
                            "\nlocalizada em:  " + entity.getRuaUnidade() + ", " + entity.getBairroUnidade() + ", " + entity.getCidadeUnidade() +". \n"+
                            "Para qualquer duvida entrar em contato no numero: " +formataNumeroTelefone(entity.getContatoUnidade())
                    );
                }else{
                    helper.setText("Olá " +entity.getNomeCliente() + "Espero que esta mensagem o encontre bem. Gostaríamos de lembrá-lo de que você tem um " +
                            "agendamento, "+entity.getNomeServico()+" para o seu adorável animalzinho"+ entity.getNomeAnimal()+ "daqui dois dias, nas horas " + entity.getHoraAgendamento() +
                            " Na unidade " + entity.getNomeUnidade() +
                            "\nlocalizada em:  " + entity.getRuaUnidade() + ", " + entity.getBairroUnidade() + ", " + entity.getCidadeUnidade()
                    );
                }


                emailSender.send(mensagem);


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String formataNumeroTelefone(String telefone){
        String numericPhoneNumber = StringUtils.getDigits(telefone);

        // Verificar se o número é válido
        if (NumberUtils.isParsable(numericPhoneNumber) && numericPhoneNumber.length() == 10) {
            // Formatar como (XXX) XXX-XXXX
            return String.format("(%s) %s-%s",
                    numericPhoneNumber.substring(0, 2),
                    numericPhoneNumber.substring(2, 6),
                    numericPhoneNumber.substring(6));
        } else {
            // Se o número não for válido, retornar a string original
            return telefone;
        }

    }

    public String formataData(String data){
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
            Date data1 = formatoEntrada.parse(data);
            SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formatoSaida.format(data1);
            return dataFormatada;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

}
