package com.gmadorell.chessfp.module.chess_game.application.create

import cats.implicits._
import cats.MonadError
import com.gmadorell.chessfp.module.chess_game.domain.model.ChessGameId
import com.gmadorell.chessfp.module.chess_game.AppError
import com.gmadorell.chessfp.module.chess_game.AppErrorTypeClasses.MonadErrorForAppError

object CreateChessGameUseCase {
  def createChessGame[P[_]: MonadError[?[_], AppError]](
    rawChessGameId: String
  ): P[Unit] = {
    for {
      chessGameId <- ChessGameId.validate(rawChessGameId)(
        MonadErrorForAppError[P]
      )
    } yield ()
  }
}
