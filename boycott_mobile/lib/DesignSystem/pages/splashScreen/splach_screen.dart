import 'package:animated_splash_screen/animated_splash_screen.dart';
import 'package:flutter/material.dart';
import '../../../DesignSystem/template/app_colors.dart';
import '../../../DesignSystem/resources/app_setting.dart';
import '../../../app/features/Home/widgets/navigation.dart';

class SplashScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return AnimatedSplashScreen(
      duration: 3000,
      splash: SizedBox(
        width: 200,
        height: 200,
        child: Image.asset(AppSetting.appLogoPath),
      ),
      splashIconSize: double.infinity,
      splashTransition: SplashTransition.scaleTransition,
      backgroundColor: AppColors.PRIMARY_COLOR,
      nextScreen: _getNextScreen(),
    );
  }

  // Check authentication status and return appropriate screen
  Widget _getNextScreen() {
    // If the user is authenticated, navigate to HomePage, otherwise show LoginPage
    return MyNavigationBar();
  }
}
