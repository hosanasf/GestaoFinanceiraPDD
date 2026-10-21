package br.com.gestaofinanceirapdd.data

import kotlinx.coroutines.flow.Flow

class LancamentoRepository(private val dao: LancamentoDao) {
    val lancamentos: Flow<List<Lancamento>> = dao.observarTodos()
    suspend fun inserir(item: Lancamento) = dao.inserir(item)
    suspend fun atualizar(item: Lancamento) = dao.atualizar(item)
    suspend fun excluir(item: Lancamento) = dao.excluir(item)
    suspend fun buscar(id: Int) = dao.buscarPorId(id)
}
