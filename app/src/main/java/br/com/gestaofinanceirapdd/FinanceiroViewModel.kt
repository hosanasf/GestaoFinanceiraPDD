package br.com.gestaofinanceirapdd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.gestaofinanceirapdd.data.*
import kotlinx.coroutines.launch

class FinanceiroViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = LancamentoRepository(AppDatabase.getDatabase(application).lancamentoDao())
    val lancamentos = repository.lancamentos
    fun salvar(item: Lancamento, aoConcluir: () -> Unit = {}) = viewModelScope.launch {
        if (item.id == 0) repository.inserir(item) else repository.atualizar(item)
        aoConcluir()
    }
    fun excluir(item: Lancamento, aoConcluir: () -> Unit = {}) = viewModelScope.launch {
        repository.excluir(item); aoConcluir()
    }
}
