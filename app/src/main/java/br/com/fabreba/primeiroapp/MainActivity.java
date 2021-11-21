package br.com.fabreba.primeiroapp;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    RadioGroup radioGroup;
    RadioButton radioButton;
    public void main(View view){
        DecimalFormat formatadorString = new DecimalFormat("0.0");
        DecimalFormat formatadorLabel = new DecimalFormat("0.00");
        TextView label1 = findViewById(R.id.label1);
        TextView label2 = findViewById(R.id.label2);
        TextView label3 = findViewById(R.id.label3);
        TextView label4 = findViewById(R.id.label4);
        TextView labelAlert = findViewById(R.id.textView12);

        double porcentagem=0;
        try {

            radioGroup = findViewById(R.id.radioGP);
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
            System.out.println(radioButton.getText());
            System.out.println("Id é"+radioId);

            EditText txtname = findViewById(R.id.editTextNumber);
            System.out.println((txtname.getText()));
            String numero =  txtname.getText().toString();
            int number = Integer.parseInt(numero);
            int[] checker = {99,999,9999,99999,999999,9999999,99999999};
            for (int i=0;i< checker.length;i++){
                if(number==checker[i]){
                    number++;
                    numero = ""+number;
                    System.out.println("opa "+number);
                    break;
                }
            }

            String mil = numero.substring(0,1);
            String cent = numero.substring(1,2);
            String dez = numero.substring(2,3);
            System.out.println(mil);
            int faixa1 =  Integer.parseInt(mil);
            int faixa2 = Integer.parseInt(cent);
            int faixa3 = Integer.parseInt(dez);
            int tamanho = numero.length();

            tamanho = tamanho-2;
            String[] a = {"#000000","#654321","#FF0000","#FF7F00","#FFFF00","#008000","#0000FF","#A020F0","#808080","#FFFAFA"};
            //                      Marrom,Vermelho,"Laranja","Amarelo","Verde","Azul" ,"Magenta","Cinza" e "Branco"
            String[] multi = {"#000000","#654321","#FF0000","#FFA500","#FFFF00","#008000","#0000FF","#A020F0","#808080"};
            System.out.println(numero);
            if(faixa3 >=5){
                faixa2+=1;
                if(faixa2==10){
                    faixa2=0;
                }
            }

            //Tolerancia
            if(radioId==R.id.radioButton3){
                label4.setBackgroundColor(Color.parseColor("#654321"));
                porcentagem = 0.01;
            }
            else if(radioId==R.id.radioButton){
                label4.setBackgroundColor(Color.parseColor("#FFD700"));
                porcentagem = 0.05;
            }
            else if(radioId==R.id.radioButton2){
                label4.setBackgroundColor(Color.parseColor("#E5E4E2"));
                porcentagem = 0.1;
            }
            System.out.println(faixa1);
            System.out.println(faixa2);
            System.out.println(faixa3);
            label1.setBackgroundColor(Color.parseColor(a[faixa1]));
            System.out.println(a[faixa1]);
            label2.setBackgroundColor(Color.parseColor(a[faixa2]));
            System.out.println(a[faixa2]);
            label3.setBackgroundColor(Color.parseColor(multi[tamanho]));
            System.out.println(multi[tamanho]);
            System.out.println("Porcentagem"+ porcentagem);

            String[] unidade = {"ohm","kohm","Mohms","Gohms"};
            String[] unidadeSearch = {" ohm","k","M","G"};
            int[] divUnit = {1,1000,1000000,1000000000};
            int div = 1;
            String unit = unidade[0];
            String searchUnit = unidadeSearch[0];
            if(number>1&&number<1000){
                div=divUnit[1];
                unit=unidade[1];
                searchUnit = unidadeSearch[1];
            }
            else if(number>1000&&number<1000000){
                div=divUnit[1];
                unit=unidade[1];
                searchUnit = unidadeSearch[1];
            }
            else if(number>1000000&&number<1000000000){
                div=divUnit[2];
                unit=unidade[2];
                searchUnit = unidadeSearch[2];
            }
            else{
                div=divUnit[3];
                unit=unidade[3];
                searchUnit = unidadeSearch[3];
            }

            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            double resistFinalMaior = (number+(porcentagem*number))/div;

            double resistFinalMenor = (number-(porcentagem*number))/div;
            String SresFinalMaior = formatadorLabel.format(resistFinalMaior);
            String SresFinalMenor = formatadorLabel.format(resistFinalMenor);
            double numberFloat = Double.parseDouble(numero);
            double resistSearch = numberFloat/div;

            String search = formatadorString.format(resistSearch);
            search = search+searchUnit;
            System.out.println(search);
            labelAlert.setText(SresFinalMenor+ "-" + SresFinalMaior + unit);
            searchNet(search);

        }
        catch (Exception e){


        Toast toast = Toast.makeText(this,"Escreva uma tolerância\n ou uma resistência",Toast.LENGTH_SHORT);
        toast.show();


        }

    }
    public void voltar(View view){
        Intent i = new Intent(MainActivity.this,TelaInicial.class);
        startActivity(i);
    }
    private void searchNet(String resistor){
        try{
            Uri uri = Uri.parse("https://br.mouser.com/c/?q="+resistor);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra(SearchManager.QUERY,resistor);
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(this, "Deu na pesquisa", Toast.LENGTH_SHORT).show();
        }
    }



}