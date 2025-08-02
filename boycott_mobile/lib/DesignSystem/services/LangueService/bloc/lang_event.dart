part of 'lang_bloc.dart';



abstract class LanguageEvent  {
  const LanguageEvent();


}

class LanguageChangeEvent extends LanguageEvent {
  final String lang;
    LanguageChangeEvent(this.lang);
}