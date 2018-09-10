package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity {
    ImageView forca;
    TextView txtPalavra, txtDica;
    Button  btnA, btnB, btnC, btnD, btnE,btnF,btnG,btnH,btnI,btnJ,btnK,btnL,btnM,btnN,btnO,btnP,btnQ,btnR,btnS,btnT,btnU,btnV,btnW,btnX,btnY,btnZ;

    // media player para tocar musica do tempo
    MediaPlayer mediaPlayer;
    AlertDialog alerta;
    String palavra[] = {
            "limao",
            "laranja",
            "fox",
            "computador",
            "AndroidStudio",
            "perfume",
            "arroz",
            "coruja",
            "teclado",
            "mouse",
            "janela",
            "onibus",
            "carro",
            "caminhao",
            "android",
            "java",
            "javaScript",
            "emanuelly",
            "caio"
    };

    String dica [] = {
            "Fruta",
            "Fruta",
            "Carro",
            "Objeto",
            "Dificil de Instalar",
            "Perfumaria",
            "Alimento",
            "Animal",
            "Objeto",
            "Objeto",
            "Objeto",
            "Transporte",
            "Transporte",
            "Transporte",
            "Linguagem de Programação",
            "Linguagem de Programação",
            "Linguagem de Programação",
            "Linda",
            "Pessoa TOP",
    };

    // Declarando um Random
    Random random;

    Drawable drawable;

    int indiceVetor, sorteio, acertos,comparacaoAcertos;
    int chances = 6;

    // Declarando uma String para receber a palavra do vetor
    String palavraSorteada;

    // Declarando uma StringBuilder para esconder  palavra
    StringBuilder palavraTraco = new StringBuilder();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPalavra = findViewById(R.id.txtPalavra);
        txtDica = findViewById(R.id.txtDica);
        forca = findViewById(R.id.forca);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnE = findViewById(R.id.btnE);
        btnF = findViewById(R.id.btnF);
        btnG = findViewById(R.id.btnG);
        btnH = findViewById(R.id.btnH);
        btnI = findViewById(R.id.btnI);
        btnJ = findViewById(R.id.btnJ);
        btnK = findViewById(R.id.btnK);
        btnL = findViewById(R.id.btnL);
        btnM = findViewById(R.id.btnM);
        btnN = findViewById(R.id.btnN);
        btnO = findViewById(R.id.btnO);
        btnP = findViewById(R.id.btnP);
        btnQ = findViewById(R.id.btnQ);
        btnR = findViewById(R.id.btnR);
        btnS = findViewById(R.id.btnS);
        btnT = findViewById(R.id.btnT);
        btnU = findViewById(R.id.btnU);
        btnV = findViewById(R.id.btnV);
        btnW = findViewById(R.id.btnW);
        btnX = findViewById(R.id.btnX);
        btnY = findViewById(R.id.btnY);
        btnZ = findViewById(R.id.btnZ);

        inicioJogo();


    }

    public void inicioJogo(){
        // criando um random (random = aleatorio)
        random = new Random();

        //pegando o tamanho do vetor e colocando na variavel
        indiceVetor = palavra.length;

        // sortendo um indice do vetor aleatorio
        sorteio = random.nextInt(indiceVetor);

        // pega o indice sorteado para gerar uma palavra
        palavraSorteada = palavra[sorteio].toUpperCase();

        // um for que conta ate o tamanho da palavra
        for (int i =0; i<palavraSorteada.length(); i++){
            // pegando a palavraTraco e colocando o traço ao lado
            palavraTraco.append("_ ");
        }
        //exibindo a palavra escondida (entre traco)
        txtPalavra.setText(palavraTraco);

        //aparecer dica quando iniciar jogo
        txtDica.setText("Dica: " + dica[sorteio]);

        // iniciar a musica de tempo
        mediaPlayer = MediaPlayer.create(this, R.raw.lamba_funk);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }

    //metodo que verifica a letra é correspondente a sorteada
    public void verificarLetra(View v) {
        //transformando a v em botao
        Button botao = (Button) v;

        //pegando o que esta escrito no botao e transformando na variavel letra
        String letra = botao.getText().toString();

        comparacaoAcertos = acertos;

        // contar o tamanho da palavra
        for (int i = 0; i < palavraSorteada.length(); i++) {

            // comparando letra por letra
            if (letra.equals(String.valueOf(palavraSorteada.charAt(i)))) {
                // ele troca o traco por letra se estiver correto , * 2 por motivo do espaço
                palavraTraco.setCharAt(i * 2, letra.charAt(0));
                acertos += 1;
            }
        }

        if (acertos == comparacaoAcertos) {
            chances--;
            // se nao houver a letra fica vermelha e desabilitada
            botao.setEnabled(false);
            botao.setBackgroundResource(R.color.vermelho);
        } else {
            // se houver a letra fica verde e desabilitada
            botao.setEnabled(false);
            botao.setBackgroundResource(R.color.verde);

        }
        //chamando a classe da imagem
        imagemErro();

        // um alert para vitoria ou derrota
        if (acertos==palavraSorteada.length()){
            alert("PARABENS!","Voce venceu");
            mediaPlayer.stop();
        }else if(chances == 0){
            alert("Voce perdeu!" +
                    "  Suas chances foram: 6"  ,
                    "Palavra: " + palavraSorteada);
            mediaPlayer.stop();
        }
        //subistituindo o traço pela palavra
        txtPalavra.setText(palavraTraco.toString());

    }

    public void alert(String titulo, String msg){
        // criando o alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(msg);
        builder.setCancelable(false);

        // Se clicado, o jogo finaliza
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // fechar a aplicação
            }
        });

        // Se clicado, o jogo reinicia, chamando o metodo reiniciar
        builder.setPositiveButton("Jogar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reiniciar();
                mediaPlayer.start();
            }
        });

        // Criando o alert
        alerta = builder.create();

        // Exibindo o alert
        alerta.show();

    }

    public void reiniciar(){

        // método que reinicia o jogo
        this.recreate();

    }

    public void imagemErro(){
        // quando errar trocar imagem
        if(chances == 6){
            drawable = getResources().getDrawable(R.drawable.forca);
        }

        if(chances == 5){
            drawable = getResources().getDrawable(R.drawable.forcaerro1);
        }

        if(chances == 4){
            drawable = getResources().getDrawable(R.drawable.forcaerro2);
        }

        if(chances == 3){
            drawable = getResources().getDrawable(R.drawable.forcaerro3);
        }

        if(chances == 2){
            drawable = getResources().getDrawable(R.drawable.forcaerro4);
        }

        if(chances == 1){
            drawable = getResources().getDrawable(R.drawable.forcaerro5);
        }

        if(chances == 0){
            drawable = getResources().getDrawable(R.drawable.forcaerro6);
        }
        //trocando as imagens
        forca.setImageDrawable(drawable);
    }
}
