package com.gmadorell.chessfp.module.chess_game

import scala.collection.immutable

import cats.MonadError
import enumeratum._

sealed trait AppError extends EnumEntry

object AppError extends Enum[AppError] {
  final case class InvalidChessGameId(invalidValue: String) extends AppError

  override val values: immutable.IndexedSeq[AppError] = findValues
}

object AppErrorTypeClasses {
  type MonadErrorForAppError[P[_]] = MonadError[P, AppError]

  object MonadErrorForAppError {
    def apply[P[_]: MonadError[?[_], AppError]]: MonadErrorForAppError[P] =
      implicitly[MonadErrorForAppError[P]]
  }
}
