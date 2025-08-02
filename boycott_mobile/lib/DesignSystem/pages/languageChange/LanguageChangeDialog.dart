import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import '../../models/Language.dart';
import '../../services/LangueService/bloc/lang_bloc.dart';
import '../../template/app_text_style.dart';
import '../../template/spaceVH.dart';
import '../../widgets/ListTileButton.dart';
import '../../widgets/containerPage.dart';

class LanguageChangeDialog extends StatelessWidget {
   LanguageChangeDialog({super.key});

   List<Language> languages=[
     Language(language: "french".tr,  codeLang: "fr"),
     Language(language: "english".tr, codeLang: "en"),
     Language(language: "arabic".tr,  codeLang: "ar"),
   ];
   String selectedLanguage=Get.locale?.languageCode??'en';



  @override
  Widget build(BuildContext context) {

    return dsContainerPage(context: context,
           title: "selectLanguage".tr,
           headerChild: Container(),
           child: BlocBuilder<LanguageBloc, LanguageState>(
               builder: (context, state) {
                 return ListView.builder(
                   shrinkWrap: true,
                   physics: const NeverScrollableScrollPhysics(),
                   itemCount: languages.length,
                   itemBuilder: (context, index){
                     return _languageOption(context, languages[index].language, languages[index].codeLang);
                   },
                 );}));
  }



  Widget _languageOption(BuildContext context, String language, String locale) {
    final languageBloc = context.read<LanguageBloc>();

    void changeLanguage(){
      languageBloc.add(LanguageChangeEvent(locale));
    }
    return Column(
      children: [
        ListTileButton(
          context:context,
          title:Text(language,style: AppTypography.body1,),
          trailing: selectedLanguage==locale? Container(
            width: 30,
            height: 30,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(15),
            ),
            child: Image.asset('assets/images/valide.png'),
          ):null,
          leading: ClipOval(
              child: SizedBox(
                      width: 30,
                      height: 30,
                      child: Image.asset('assets/images/lang/$locale.png')),),
          onPressed: changeLanguage

        ),
        verticalSpaceSmall
      ],
    );
  }
}
