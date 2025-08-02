import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

import '../template/app_colors.dart';

class CustomTheme {
  static const lightThemeFont = "ComicNeue", darkThemeFont = "Poppins";

  // light theme
  static final lightTheme = ThemeData(
    primaryColor: white,
    brightness: Brightness.light,
    scaffoldBackgroundColor: white,
    useMaterial3: true,
    fontFamily: lightThemeFont,
    switchTheme: SwitchThemeData(
      thumbColor:
      MaterialStateProperty.resolveWith<Color>((states) => white),
    ),
    appBarTheme: AppBarTheme(
      centerTitle: true,
      backgroundColor: AppColors.PRIMARY_COLOR,
      scrolledUnderElevation: 0,
      titleTextStyle: const TextStyle(
        fontWeight: FontWeight.w500,
        color: AppColors.WHITE_COLOR,
        fontSize: 23, //20
      ),
      iconTheme:  IconThemeData(color: white),
      elevation: 0,
      actionsIconTheme: IconThemeData(color: white),
      systemOverlayStyle: SystemUiOverlayStyle(
        statusBarColor: darkThemeColor,
        statusBarIconBrightness: Brightness.dark,
      ),


    ),
    iconTheme:  IconThemeData(
      color: white,
    ),
    elevatedButtonTheme: ElevatedButtonThemeData(
      style: ButtonStyle(
        foregroundColor:  MaterialStateProperty.resolveWith<Color>((states) => AppColors.BLACK_COLOR),
        shadowColor:MaterialStateProperty.resolveWith<Color>((states) => Get.isDarkMode?white:black )

      )
    )

  );

  // dark theme
  static final darkTheme = ThemeData(
    primaryColor: darkThemeColor,
    brightness: Brightness.dark,
    scaffoldBackgroundColor: Colors.black,
    useMaterial3: true,
    fontFamily: darkThemeFont,
    switchTheme: SwitchThemeData(
      trackColor:
      MaterialStateProperty.resolveWith<Color>((states) => darkThemeColor),
    ),
    appBarTheme: AppBarTheme(
      centerTitle: true,
      backgroundColor:AppColors.PRIMARY_COLOR_DARK,
      scrolledUnderElevation: 0,
      titleTextStyle: TextStyle(
        fontWeight: FontWeight.w500,
        color: white,
        fontSize: 23, //20
      ),
      iconTheme: IconThemeData(color:white),
      elevation: 0,
      actionsIconTheme: IconThemeData(color: darkThemeColor),
      systemOverlayStyle: const SystemUiOverlayStyle(
        statusBarColor: AppColors.PRIMARY_COLOR_DARK,
        statusBarIconBrightness: Brightness.light,

      ),

    ),
    iconTheme: const IconThemeData(
      color: AppColors.PRIMARY_COLOR_LIGHT,
    ),
    iconButtonTheme: IconButtonThemeData(
      style: ButtonStyle(
        iconColor:MaterialStateProperty.resolveWith<Color>((states) =>  Colors.white,),
      )
    ),
      elevatedButtonTheme: ElevatedButtonThemeData(
          style: ButtonStyle(
            foregroundColor:  MaterialStateProperty.resolveWith<Color>((states) => AppColors.PRIMARY_COLOR_LIGHT),

          )
      )
  );

  // colors
  static Color
      white = AppColors.WHITE_COLOR,
      black = Colors.black26,
      darkThemeColor = AppColors.PRIMARY_COLOR;
}
