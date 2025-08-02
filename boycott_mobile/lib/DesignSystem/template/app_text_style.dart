import 'package:flutter/material.dart';


import 'app_colors.dart';
class AppTypography {
  static  TextStyle headline1 = TextStyle(
    fontSize: 32,
    fontWeight: FontWeight.bold
  ).copyWith(color: AppColors.PRIMARY_COLOR);

  static const TextStyle headline2 = TextStyle(
    fontSize: 28,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle headline3 = TextStyle(
    fontSize: 24,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle headline4 = TextStyle(
    fontSize: 20,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle headline5 = TextStyle(
    fontSize: 20,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle headline6 = TextStyle(
    fontSize: 18,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle subtitle1 = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.bold,
  );

  static const TextStyle subtitle2 = TextStyle(
    fontSize: 14,
    fontWeight: FontWeight.bold,
  );

  static  TextStyle body1 = TextStyle(
    fontSize: 16,


  );

  static const TextStyle body2 = TextStyle(
    fontSize: 14,
  );

  static const TextStyle caption = TextStyle(
    fontSize: 12,
  );

  static const TextStyle button = TextStyle(
    fontSize: 16,
    fontWeight: FontWeight.bold,
    color: Colors.white,
  );

}
