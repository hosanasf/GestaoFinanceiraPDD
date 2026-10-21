package br.com.gestaofinanceirapdd.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LancamentoDao_Impl implements LancamentoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Lancamento> __insertionAdapterOfLancamento;

  private final EntityDeletionOrUpdateAdapter<Lancamento> __deletionAdapterOfLancamento;

  private final EntityDeletionOrUpdateAdapter<Lancamento> __updateAdapterOfLancamento;

  public LancamentoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLancamento = new EntityInsertionAdapter<Lancamento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `lancamentos` (`id`,`titulo`,`categoria`,`valor`,`tipo`,`data`,`observacao`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Lancamento entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getCategoria());
        statement.bindDouble(4, entity.getValor());
        statement.bindString(5, entity.getTipo());
        statement.bindString(6, entity.getData());
        statement.bindString(7, entity.getObservacao());
      }
    };
    this.__deletionAdapterOfLancamento = new EntityDeletionOrUpdateAdapter<Lancamento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `lancamentos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Lancamento entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfLancamento = new EntityDeletionOrUpdateAdapter<Lancamento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `lancamentos` SET `id` = ?,`titulo` = ?,`categoria` = ?,`valor` = ?,`tipo` = ?,`data` = ?,`observacao` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Lancamento entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getCategoria());
        statement.bindDouble(4, entity.getValor());
        statement.bindString(5, entity.getTipo());
        statement.bindString(6, entity.getData());
        statement.bindString(7, entity.getObservacao());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object inserir(final Lancamento lancamento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLancamento.insert(lancamento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object excluir(final Lancamento lancamento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLancamento.handle(lancamento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object atualizar(final Lancamento lancamento,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLancamento.handle(lancamento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Lancamento>> observarTodos() {
    final String _sql = "SELECT * FROM lancamentos ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"lancamentos"}, new Callable<List<Lancamento>>() {
      @Override
      @NonNull
      public List<Lancamento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfObservacao = CursorUtil.getColumnIndexOrThrow(_cursor, "observacao");
          final List<Lancamento> _result = new ArrayList<Lancamento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Lancamento _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            final String _tmpObservacao;
            _tmpObservacao = _cursor.getString(_cursorIndexOfObservacao);
            _item = new Lancamento(_tmpId,_tmpTitulo,_tmpCategoria,_tmpValor,_tmpTipo,_tmpData,_tmpObservacao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object buscarPorId(final int id, final Continuation<? super Lancamento> $completion) {
    final String _sql = "SELECT * FROM lancamentos WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Lancamento>() {
      @Override
      @Nullable
      public Lancamento call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfObservacao = CursorUtil.getColumnIndexOrThrow(_cursor, "observacao");
          final Lancamento _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpCategoria;
            _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            final String _tmpObservacao;
            _tmpObservacao = _cursor.getString(_cursorIndexOfObservacao);
            _result = new Lancamento(_tmpId,_tmpTitulo,_tmpCategoria,_tmpValor,_tmpTipo,_tmpData,_tmpObservacao);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
//Pedir ajuda para resolver essa etapa, está MUITO complicada e apresentando muitos erros