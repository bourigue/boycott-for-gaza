// Define routes
import 'package:flutter/cupertino.dart';
import '../../DesignSystem/pages/languageChange/LanguageChangeDialog.dart';
import '../../DesignSystem/pages/settings.dart';
import '../../DesignSystem/pages/splashScreen/splach_screen.dart';
import '../../DesignSystem/pages/themeChange/ThemeChangeDialog.dart';
import 'Home/home.dart';
import 'Home/widgets/navigation.dart';

final Map<String, WidgetBuilder> routes = {
  //layout
  '/splashScreen': (context) => SplashScreen(),
  '/homePage': (context) => HomePage(),
  '/bottomNavigation': (context) => MyNavigationBar(),

  // features
  '/settings': (context) => const Setting(),
  '/LanguageChangeDialog': (context) => LanguageChangeDialog(),
  '/themeChangeDialog': (context) => ThemeChangeDialog(),
};
