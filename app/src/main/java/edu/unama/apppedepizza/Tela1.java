package edu.unama.apppedepizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

public class Tela1 extends AppCompatActivity {
    //1) declaração de variáveis para componentes dinâmicos
    EditText editNome, editEndereco;
    Spinner spinSaborPizza;
    RadioGroup rgTamanho;
    Switch swRefrigerante;
    RatingBar rbAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        //2) integração XML e Java:
        editNome = (EditText) findViewById(R.id.et_nome);
        editEndereco = (EditText) findViewById(R.id.et_endereco);
        spinSaborPizza = (Spinner) findViewById(R.id.spin_sabor);
        rgTamanho = (RadioGroup) findViewById(R.id.rg_tamanho);
        swRefrigerante = (Switch) findViewById(R.id.sw_refrigerante);
        rbAvaliacao = (RatingBar) findViewById(R.id.rate_pedido);
    } //fim do onCreate()

    //3) tornar o menu visível:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //4) controle do clique (ação)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelecionado = item.getItemId();

        if(itemSelecionado == R.id.acao_pedir) {
            //5) pegar os valores informados na Tela1
            String nomeDigitado = editNome.getText().toString();
            String enderecoDigitado = editEndereco.getText().toString();
            String saborEscolhido = spinSaborPizza.getSelectedItem().toString();
            String tamanhoEscolhido = "";
            double valorPizza = 0.0;
            int idRadioTamanhoEscolhido = rgTamanho.getCheckedRadioButtonId();
            switch (idRadioTamanhoEscolhido) {
                case R.id.rb_pequena:
                    tamanhoEscolhido = "Pequena";
                    valorPizza = valorPizza + 25.0;
                    break;
                case R.id.rb_media:
                    tamanhoEscolhido = "Média";
                    valorPizza = valorPizza + 38.0;
                    break;
                case R.id.rb_grande:
                    tamanhoEscolhido = "Grande";
                    valorPizza = valorPizza + 45.0;
                    break;
                default:
                    tamanhoEscolhido = "Inválido";
            } //fim do switch
            if( swRefrigerante.isChecked() ) {
                valorPizza = valorPizza + 7.0;
            }
            String respRefri = swRefrigerante.isChecked() == true ? "SIM" : "NÃO";
            double avaliacaoEscolhida = rbAvaliacao.getRating();

            //6) passar os valores para a Tela2
            //Intent Explícita
            Intent irTela2 = new Intent(this, Tela2.class);

            irTela2.putExtra("nome", nomeDigitado);
            irTela2.putExtra("endereco", enderecoDigitado);
            irTela2.putExtra("sabor", saborEscolhido);
            irTela2.putExtra("tamanho", tamanhoEscolhido);
            irTela2.putExtra("refrigerante", respRefri);
            irTela2.putExtra("avaliacao", avaliacaoEscolhida);
            irTela2.putExtra("valor", valorPizza);

            startActivity( irTela2 );

        }

        return super.onOptionsItemSelected(item);
    }


} //fim da classe Tela1
