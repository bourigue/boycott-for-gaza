part of 'user_bloc.dart';

@immutable
sealed class UserEvent {}

class GetUserByIdEvent extends UserEvent {
  User user;

  GetUserByIdEvent(this.user);
}
