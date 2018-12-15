package com.gmadorell.chessfp.module.chess_game.application.create

import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.MonadError
import com.gmadorell.chessfp.module.chess_game.domain.model.{
  ChessGame,
  ChessGameId
}
import com.gmadorell.chessfp.module.chess_game.AppError

object CreateChessGameUseCase {
  def createChessGame[P[_]: MonadError[?[_], AppError]: ChessGameAlgebra](
    rawChessGameId: String
  ): P[Unit] = {
    for {
      chessGameId <- ChessGameId.validate(rawChessGameId)
      chessGame = ChessGame(chessGameId)
      _ <- ChessGameAlgebra[P].create(chessGame)
    } yield ()
  }
}

trait ChessGameAlgebra[P[_]] {
  def create(chessGame: ChessGame): P[Unit]
}

object ChessGameAlgebra {
  def apply[P[_]: ChessGameAlgebra]: ChessGameAlgebra[P] =
    implicitly[ChessGameAlgebra[P]]
}
