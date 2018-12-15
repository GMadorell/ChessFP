package com.gmadorell.chessfp.module.chess_game.behaviour

import cats.data.StateT
import cats.implicits._
import cats.scalatest.EitherMatchers
import com.danielasfregola.randomdatagenerator.RandomDataGenerator._
import com.gmadorell.chessfp.module.chess_game.application.create.{
  ChessGameAlgebra,
  CreateChessGameUseCase
}
import com.gmadorell.chessfp.module.chess_game.domain.model.ChessGame
import com.gmadorell.chessfp.module.chess_game.domain.model.ChessGameId.ChessGameId
import com.gmadorell.chessfp.module.chess_game.AppError
import eu.timepit.refined.auto._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{Matchers, WordSpec}

final case class ChessGameState(chessGames: List[ChessGame] = List.empty)

final class CreateChessGameUseCaseSpec
    extends WordSpec
    with Matchers
    with EitherMatchers {
  type P[A] = StateT[Either[AppError, ?], ChessGameState, A]

  implicit val testChessGameAlgebra: ChessGameAlgebra[P] =
    new ChessGameAlgebra[P] {
      override def create(chessGame: ChessGame): P[Unit] =
        StateT.modify(
          oldState =>
            oldState.copy(chessGames = oldState.chessGames :+ chessGame)
        )
    }

  "A CreateChessGameUseCase" should {
    "create a chess game" in {

      implicit val chessGameIdArbitrary: Arbitrary[ChessGameId] = Arbitrary {
        Gen.const("21278ed6-1027-4a22-9c3f-af4c3f8834d1")
      }

      val chessGameId = random[ChessGameId]
      val initialState = ChessGameState(chessGames = List.empty)
      val finalState =
        initialState.copy(chessGames = List(ChessGame(chessGameId)))

      val program = CreateChessGameUseCase.createChessGame[P](chessGameId)
      val runResult = program.runS(initialState)
      runResult should beRight(finalState)
    }
  }
}
