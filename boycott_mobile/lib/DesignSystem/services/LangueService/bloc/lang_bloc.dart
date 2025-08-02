import 'dart:ui';
import 'package:bloc/bloc.dart';
import 'package:get/get.dart';
part 'lang_event.dart';
part 'lang_state.dart';

class LanguageBloc extends Bloc<LanguageEvent, InitialLanguageState> {


  LanguageBloc() : super(InitialLanguageState()) {
    on<LanguageEvent>((event, emit) async {
      if (event is LanguageChangeEvent) {
        Locale local = Locale(event.lang);
        Get.updateLocale(local);
      }
    });
  }
}

