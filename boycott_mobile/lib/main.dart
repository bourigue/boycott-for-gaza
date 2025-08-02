import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'DesignSystem/pages/splashScreen/splach_screen.dart';
import 'DesignSystem/resources/theme.dart';
import 'DesignSystem/services/LangueService/Translate.dart';
import 'DesignSystem/services/LangueService/bloc/lang_bloc.dart';
import 'DesignSystem/template/app_colors.dart';
import 'app/features/routing.dart';
import 'di_container.dart';
import 'package:shared_preferences/shared_preferences.dart';

SharedPreferences? sharepref;

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  sharepref = await SharedPreferences.getInstance();

  SystemChrome.setSystemUIOverlayStyle(
      const SystemUiOverlayStyle(statusBarColor: AppColors.PRIMARY_COLOR));
  init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider<LanguageBloc>(
            create: (BuildContext context) => LanguageBloc()),
      ],
      child: GetMaterialApp(
        debugShowCheckedModeBanner: false,
        title: "EM",
        locale: Get.deviceLocale,
        translations: MyLocal(),
        routes: routes,
        theme: CustomTheme.lightTheme,
        darkTheme: CustomTheme.darkTheme,
        themeMode: ThemeMode.system,
        home: SplashScreen(),
      ),
    );
  }
}
