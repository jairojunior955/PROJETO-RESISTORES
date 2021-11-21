package br.com.fabreba.primeiroapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void main(View view){
        DecimalFormat formatadorLabel = new DecimalFormat("0.00");
        Spinner spin1 = findViewById(R.id.faixa1);
        Spinner spin2 = findViewById(R.id.faixa2);
        Spinner spin3 = findViewById(R.id.faixa3);
        Spinner spin4 = findViewById(R.id.faixa4);
        int idItem = (int) spin4.getSelectedItemId();
        String text1 = spin1.getSelectedItem().toString();
        String text2 = spin2.getSelectedItem().toString();
        String text3 = spin3.getSelectedItem().toString();
        String text4 = spin4.getSelectedItem().toString();
        TextView label1 = findViewById(R.id.label1);
        TextView label2 = findViewById(R.id.label2);
        TextView label3 = findViewById(R.id.label3);
        TextView label4 = findViewById(R.id.label4);
        TextView label5 = findViewById(R.id.labelFinal);
        String faixa1 = text1.substring(0,1);
        String faixa2 = text2.substring(0,1);
        String faixa3 = text3.substring(0,1);
        String faixa4 = text4.substring(0,2);

        String faixa4Ref = faixa4.replaceAll(" ","");
        int num1 = Integer.parseInt(faixa1);
        int num2 = Integer.parseInt(faixa2);
        int num3 = Integer.parseInt(faixa3);
        double tolMenor = (100-Integer.parseInt(faixa4Ref));
        double tolMaior = (100+Integer.parseInt(faixa4Ref));
        System.out.println(tolMenor+" - "+ tolMaior);
        String[] a = {"#000000","#654321","#FF0000","#FF7F00","#FFFF00","#008000","#0000FF","#A020F0","#808080","#FFFAFA"};
        String[] multi = {"#000000","#654321","#FF0000","#FFA500","#FFFF00","#008000","#0000FF","#A020F0","#808080","#FFFFFF"};
        String[] tol = {"#654321","#FF0000","#FFD700","#E5E4E2"};
        String[] unidades = {"ohms","Kohms","Mohms","Gohms"};
        String unidadeFinal = "";
        String unidadeFinalMaior = "";

        try {
            label1.setBackgroundColor(Color.parseColor(a[num1]));
            label2.setBackgroundColor(Color.parseColor(a[num2]));
            label3.setBackgroundColor(Color.parseColor(multi[num3]));
            label4.setBackgroundColor(Color.parseColor(tol[idItem]));
            String resultado;
            resultado = faixa1+faixa2;
            System.out.println("num3 é "+num3);
             int multiplicador = (int) Math.pow(10,num3);
            String unidadeFinalMenor = "";

            double resultadoDouble = Integer.parseInt(resultado);
            resultadoDouble = resultadoDouble*multiplicador;
            double resistMenor = resultadoDouble*(tolMenor/100);
            double resistMaior = resultadoDouble*(tolMaior/100);
            if(num3<4){
                unidadeFinal =  unidades[0];
            }
            else if(num3>=4 && num3<6){
                unidadeFinal =  unidades[1];
                resistMenor = resultadoDouble*(tolMenor/100)/1000;
                resistMaior = resultadoDouble*(tolMaior/100)/1000;
                unidadeFinalMenor = formatadorLabel.format(resistMenor);
                unidadeFinalMaior = formatadorLabel.format(resistMaior);

            }
            else if(num3>=6 && num3<9) {
                unidadeFinal = unidades[2];
                resistMenor = resultadoDouble * (tolMenor / 100) / 1000000;
                resistMaior = resultadoDouble * (tolMaior / 100) / 1000000;
                unidadeFinalMenor = formatadorLabel.format(resistMenor);
                unidadeFinalMaior = formatadorLabel.format(resistMaior);
            }
            else{
                unidadeFinal =  unidades[3];
                resistMenor = (resultadoDouble*(tolMenor/100))/1000000000;
                resistMaior = (resultadoDouble*(tolMaior/100))/1000000000;
                System.out.println(resistMenor);
                System.out.println(resistMaior);
                unidadeFinalMenor = formatadorLabel.format(resistMenor);
                unidadeFinalMaior = formatadorLabel.format(resistMaior);
            }
            System.out.println(resistMenor);
            System.out.println(resistMaior);
            if(num3<9) {
                label5.setText(resistMenor + unidadeFinal + " - " + resistMaior + unidadeFinal);
            }
            else{
                label5.setText(unidadeFinalMenor + unidadeFinal + " - " + unidadeFinalMaior + unidadeFinal);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void voltar(View view){
        Intent i = new Intent(MainActivity2.this,TelaInicial.class);
        startActivity(i);

    }
}