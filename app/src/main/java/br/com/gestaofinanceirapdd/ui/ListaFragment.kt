package br.com.gestaofinanceirapdd.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.fragment.app.commit
import br.com.gestaofinanceirapdd.FinanceiroViewModel
import br.com.gestaofinanceirapdd.R
import br.com.gestaofinanceirapdd.data.Lancamento
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class ListaFragment : Fragment() {
    private val vm: FinanceiroViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_lista, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lista = view.findViewById<LinearLayout>(R.id.listaLancamentos)
        view.findViewById<Button>(R.id.btnNovo).setOnClickListener { parentFragmentManager.commit { replace(R.id.conteudo, FormularioFragment()); addToBackStack(null) } }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.lancamentos.collect { itens ->
                    lista.removeAllViews()
                    if (itens.isEmpty()) { val empty = TextView(requireContext()); empty.text = "Nenhum lançamento ainda. Toque em + Novo lançamento."; empty.textSize = 16f; empty.setPadding(8, 24, 8, 24); lista.addView(empty) }
                    itens.forEach { item ->
                        val card = LinearLayout(requireContext()).apply { orientation = LinearLayout.VERTICAL; setPadding(18, 14, 18, 14); setBackgroundResource(android.R.drawable.dialog_holo_light_frame) }
                        val title = TextView(requireContext()).apply { text = item.titulo; textSize = 18f; setTextColor(0xFF174536.toInt()) }
                        val details = TextView(requireContext()).apply { text = "${item.categoria} • ${item.data} • ${item.tipo}\n${NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(item.valor)}"; textSize = 15f }
                        val actions = LinearLayout(requireContext()).apply { orientation = LinearLayout.HORIZONTAL }
                        val edit = Button(requireContext()).apply { text = "Detalhes / editar"; setOnClickListener { abrirEdicao(item) } }
                        val delete = Button(requireContext()).apply { text = "Excluir"; setOnClickListener { confirmarExclusao(item) } }
                        actions.addView(edit, LinearLayout.LayoutParams(0, -2, 1f)); actions.addView(delete)
                        card.addView(title); card.addView(details); card.addView(actions)
                        val lp = LinearLayout.LayoutParams(-1, -2); lp.setMargins(0, 0, 0, 12); lista.addView(card, lp)
                    }
                }
            }
        }
    }
    private fun abrirEdicao(item: Lancamento) {
        parentFragmentManager.commit { replace(R.id.conteudo, FormularioFragment().apply { arguments = Bundle().apply { putInt("id", item.id); putString("titulo", item.titulo); putString("categoria", item.categoria); putString("tipo", item.tipo); putString("data", item.data); putString("observacao", item.observacao); putDouble("valor", item.valor) } }); addToBackStack(null) }
    }
    private fun confirmarExclusao(item: Lancamento) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext()).setTitle("Excluir lançamento?").setMessage("Deseja excluir ${item.titulo}?").setNegativeButton("Cancelar", null).setPositiveButton("Excluir") { _, _ -> vm.excluir(item) }.show()
    }
}
