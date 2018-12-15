package com.gmadorell.chessfp.module.chess_game.domain.model

import cats.{Monad, MonadError}
import cats.implicits._
import com.gmadorell.chessfp.module.chess_game.AppError
import com.gmadorell.chessfp.module.chess_game.AppError.InvalidChessGameId
import com.gmadorell.chessfp.module.chess_game.AppErrorTypeClasses.MonadErrorForAppError
import eu.timepit.refined.api.{Refined, RefType}
import eu.timepit.refined.string.Uuid

object ChessGameId {
  type ChessGameId = String Refined Uuid

  def validate[P[_]: MonadError[?[_], AppError]](raw: String): P[ChessGameId] =
    RefType
      .applyRef[ChessGameId](raw)
      .fold(
        _ => MonadErrorForAppError[P].raiseError(InvalidChessGameId(raw)),
        Monad[P].pure
      )
}
