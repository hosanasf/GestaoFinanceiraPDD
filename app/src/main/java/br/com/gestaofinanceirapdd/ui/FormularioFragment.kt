package br.com.gestaofinanceirapdd.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import br.com.gestaofinanceirapdd.FinanceiroViewModel
import br.com.gestaofinanceirapdd.R
import br.com.gestaofinanceirapdd.data.Lancamento
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormularioFragment : Fragment() {
    private val vm: FinanceiroViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_formulario, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments
        val titulo = view.findViewById<EditText>(R.id.edTitulo)
        val categoria = view.findViewById<EditText>(R.id.edCategoria)
        val valor = view.findViewById<EditText>(R.id.edValor)
        val data = view.findViewById<EditText>(R.id.edData)
        val observacao = view.findViewById<EditText>(R.id.edObservacao)
        val tipo = view.findViewById<Spinner>(R.id.spTipo)
        tipo.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listOf("Despesa", "Receita"))
        titulo.setText(args?.getString("titulo") ?: "")
        categoria.setText(args?.getString("categoria") ?: "")
        valor.setText(if (args != null) args.getDouble("valor").toString() else "")
        data.setText(args?.getString("data") ?: SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date()))
        observacao.setText(args?.getString("observacao") ?: "")
        tipo.setSelection(if (args?.getString("tipo") == "Receita") 1 else 0)
        view.findViewById<TextView>(R.id.txtTituloFormulario).text = if (args?.containsKey("id") == true) "Editar lançamento" else "Novo lançamento"
        view.findViewById<Button>(R.id.btnSalvar).setOnClickListener {
            val nome = titulo.text.toString().trim(); val cat = categoria.text.toString().trim(); val amount = valor.text.toString().trim().replace(',', '.').toDoubleOrNull(); val dia = data.text.toString().trim()
            if (nome.isBlank() || cat.isBlank() || amount == null || amount <= 0 || dia.isBlank()) { Toast.makeText(requireContext(), "Preencha título, categoria, valor positivo e data.", Toast.LENGTH_LONG).show(); return@setOnClickListener }
            val item = Lancamento(args?.getInt("id") ?: 0, nome, cat, amount, tipo.selectedItem.toString(), dia, observacao.text.toString().trim())
            vm.salvar(item) { parentFragmentManager.popBackStack() }
        }
        view.findViewById<Button>(R.id.btnCancelar).setOnClickListener { parentFragmentManager.popBackStack() }
    }
}
