package edu.unama.apppedepizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tela2 extends AppCompatActivity {
    //1) declaração de componentes dinâmicos (Tela2)
    TextView txtNome, txtEndereco, txtPizza, txtRefri, txtValor,
            txtAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        //2) integraçao XML e Java:
        txtNome = (TextView) findViewById(R.id.tela2_nome);
        txtEndereco = (TextView) findViewById(R.id.tela2_endereco);
        txtPizza = (TextView) findViewById(R.id.tela2_pizza);
        txtRefri = (TextView) findViewById(R.id.tela2_refrigerante);
        txtValor = (TextView) findViewById(R.id.tela2_valor);
        txtAvaliacao = (TextView) findViewById(R.id.tela2_avalicao);

        //3) pegar a Intent repassada:
        Intent i = getIntent();
        String nomeTela1 = "", enderecoTela1 = "", saborTela1 = ""
                , tamanhoTela1 = "", respRefriTela1 = "";
        double avaliacaoTela1 = 0.0, valorPizzaTela1 = 0.0;
        if(i.hasExtra("nome")) {
            nomeTela1 = i.getStringExtra("nome");
        }
        if(i.hasExtra("endereco")) {
            enderecoTela1 = i.getStringExtra("endereco");
        }
        if(i.hasExtra("sabor")) {
            saborTela1 = i.getStringExtra("sabor");
        }
        if(i.hasExtra("tamanho")) {
            tamanhoTela1 =  i.getStringExtra("tamanho");
        }
        if(i.hasExtra("refrigerante")) {
            respRefriTela1 = i.getStringExtra("refrigerante");
        }
        if(i.hasExtra("avaliacao")) {
            avaliacaoTela1 = i.getDoubleExtra("avaliacao", 0.0);
        }
        if(i.hasExtra("valor")) {
            valorPizzaTela1 = i.getDoubleExtra("valor", 0.0);
        }
        //4) atribuir os valores nos TextViews
        txtNome.setText( "Nome: " + nomeTela1 );
        txtEndereco.setText( enderecoTela1 );
        txtPizza.setText( saborTela1 + " (" + tamanhoTela1 + ")" );
        txtRefri.setText( "Refrigerante 2L: " + respRefriTela1 );
        txtValor.setText( "R$ " + valorPizzaTela1 );
        txtAvaliacao.setText( String.valueOf(avaliacaoTela1) );

    } //fim do onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela2, menu);
        return true;
    } //fim do onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelecionado = item.getItemId();

        if(itemSelecionado == R.id.acao_compartilhar) {
            //criar a frase para compartilhar:
            String frase = "Acabei de pedir uma pizza " + txtPizza.getText() +
                    ". Peça você também pelo app <endereco do app>.";

            //criar uma Intent Implícita:
            Intent j = new Intent(Intent.ACTION_SEND);
            j.setType("text/plain");
            j.putExtra(Intent.EXTRA_SUBJECT, "Pedido de pizza");
            j.putExtra(Intent.EXTRA_TEXT, frase);

            startActivity( Intent.createChooser(j, "Compartilhar...") );

        }

        return super.onOptionsItemSelected(item);
    }
} //fim da classe Tela2




