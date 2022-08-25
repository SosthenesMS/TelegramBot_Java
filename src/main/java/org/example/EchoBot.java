package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class EchoBot extends TelegramLongPollingBot{

    // Função obrigatória da classe TelegramLongPollingBot
    @Override
    public String getBotUsername() {
        return getBotNomeDoUsuario();
    }

    // Função obrigatória da classe TelegramLongPollingBot
    @Override
    public String getBotToken() {
        return getBotChaveToken();
    }

    // Função obrigatória da classe TelegramLongPollingBot
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage mensagem = responder(update);
            try {
                execute(mensagem);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getBotChaveToken(){
        return "5580086888:AAH3oI8ehD-TRs2b9TUdmUVUEd8R72Uzkr4";
    }

    public static String getBotNomeDoUsuario(){
        return "@May9th_bot";
    }

    private SendMessage responder(Update update) {
        String textoMensagem = update.getMessage().getText().toLowerCase();
        String chatId = update.getMessage().getChatId().toString();
        String resposta = "";

        if(textoMensagem.startsWith("data") || textoMensagem.startsWith("1")){
            resposta = getData();
        } else if(textoMensagem.startsWith("hora") || textoMensagem.startsWith("2") ){
            resposta = getHora();
        } else if(textoMensagem.startsWith("tela") || textoMensagem.startsWith("3")) {
            resposta = getResolucao();
        } else if(textoMensagem.startsWith("me conte uma piada nerd") ||
                 textoMensagem.startsWith("me conte uma piada nerd!") ||
                 textoMensagem.startsWith("me conta uma piada nerd") ||
                 textoMensagem.startsWith("me conta uma piada nerd!") ||
                 textoMensagem.startsWith("piada") || textoMensagem.startsWith("4")) {
            resposta = getPiadaNerd();
        } else if(textoMensagem.startsWith("info") || textoMensagem.startsWith("5")){
            resposta = "\uD83D\uDE4B\u200D♂️Olá! Eu me chamo Elliot, sou um bot interativo! \n" +
                    "Fui desenvolvido para estudos ultilizando Java 17 em Command-Lines, criação de métodos, Arrays, estruturas de repetição e \n" +
                    "a Api do telegram!";
        } else if (textoMensagem.startsWith("help") || textoMensagem.startsWith("/start")){
            resposta = "Opções:\n1.Data\n2.Hora\n3.Tela\n4.Me conta uma piada nerd!\n5.Info";
        } else {
            resposta = "Não entendi!\nDigite /help para ver as opções disponíveis!";
        }
        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

    public static String getData(){
        LocalDate data = LocalDate.now();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyy");
        return "A data de hoje é: " + data.format(dt);
    }

    public static String getHora(){
        LocalTime hora = LocalTime.now();
        DateTimeFormatter hr = DateTimeFormatter.ofPattern("HH:mm");
        return "São exatamente " + hora.format(hr);
    }

    public static String getResolucao(){
        Dimension resolucao = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int largura = (int)resolucao.getWidth();
        int altura = (int)resolucao.getHeight();
        return "A resolução atual é de (" + largura + " X " + altura + ")pixels";
    }

    public static String getPiadaNerd() {
        Random gerador = new Random();
        int numeroAleatorio = gerador.nextInt(16);
        String[] piadas = {
                "P: O que um etanol disse pro outro?\n\nR: Etanóis…",
                "P: O que três pontos não colineares disseram um ao outro?\n\nR: Temos um plano",
                "P: O que é pior que ser atingido por um raio?\n\nR: ser atingido pelo diâmetro, que tem o dobro do tamanho!!",
                "P: Por que o Hulk grita quando se tranforma?\n\nR: Porque a cueca dele não rasga",
                "P: Qual o herói que tinha uma pequena sobremesa?\n\nR: O Thor tinha",
                "P: Como o Batman dorme?\n\nR:De Bruce","P: Qual o herói que serve para temperar?\n\nR:O Archeiro Verde",
                "P: Qual o herói mais religioso de todos?\n\nR: A Aqua Amém","P: Onde comprar comida para um Super-Herói?\n\nR: No Super-Mercado",
                "P: O que aconteceu quando o Super-Homem morreu?\n\nR: O Lex ficou de Luthor",
                "P: Qual é o lanche preferido do Professor Xavier?\n\nR: X-Burguer",
                "P: Qual o herói que já está ficando velhinho?\n\nR: O Vôverine","P: Que roupa Goku deu para a namorada?\n\nR: Uma super saia jeans",
                "P: Qual é a melhor logística do Brasil?\n\nR: M.dias branco! hahahaha \n Ah, desculpa, foi uma piada interna, não consegui segurar! \uD83D\uDC81\u200D♂️",
                "P: Como o Batman faz para abrir a bat-caverna?\n\nR: Ele bat-palma!"
        };
        return piadas[numeroAleatorio];
    }
}
