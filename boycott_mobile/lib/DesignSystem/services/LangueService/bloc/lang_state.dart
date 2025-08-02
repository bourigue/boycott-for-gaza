part of 'lang_bloc.dart';

abstract class LanguageState {
  const LanguageState();
}

class InitialLanguageState extends LanguageState {

}

class LanguageChangedState extends LanguageState {

   languageChanged(){}
}
