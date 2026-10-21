package br.com.gestaofinanceirapdd.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LancamentoDao {
    @Query("SELECT * FROM lancamentos ORDER BY id DESC")
    fun observarTodos(): Flow<List<Lancamento>>

    @Query("SELECT * FROM lancamentos WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Lancamento?

    @Insert
    suspend fun inserir(lancamento: Lancamento)

    @Update
    suspend fun atualizar(lancamento: Lancamento)

    @Delete
    suspend fun excluir(lancamento: Lancamento)
}
