import 'dart:ui';

import 'package:get/get.dart';
class MyLocalController extends GetxController{

void changeLang(String codelang){
  Locale local=Locale(codelang);
  Get.updateLocale(local);
}
}