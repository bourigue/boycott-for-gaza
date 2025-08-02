import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

import '../../models/User.dart';

part 'user_event.dart';
part 'user_state.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc() : super(UserInitial()) {
    on<GetUserByIdEvent>((event, emit) {
      emit(UserLoading());
      try {} catch (error) {}
    });
  }
}
