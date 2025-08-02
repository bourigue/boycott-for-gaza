import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';

import '../template/app_colors.dart';
import '../template/spaceVH.dart';
import 'appBar.dart';

Widget dsContainerPage(
    {required BuildContext context,
    required Widget headerChild,
    required Widget child,
    required String title}) {
  return SafeArea(
    child: Scaffold(
      appBar: dsAppBar(context, title) as PreferredSizeWidget?,
      backgroundColor: Get.isDarkMode
          ? AppColors.PRIMARY_COLOR_DARK
          : AppColors.PRIMARY_COLOR,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Expanded(
            child: Container(
                height: screenHeight(context),
                padding: const EdgeInsets.all(30),
                decoration: BoxDecoration(
                    color:
                        Get.isDarkMode ? Colors.black : AppColors.WHITE_COLOR,
                    borderRadius: BorderRadius.only(
                        topRight: Radius.circular(30),
                        topLeft: Radius.circular(30))),
                child: SingleChildScrollView(
                  child: Column(
                    children: [
                      headerChild,
                      child,
                    ],
                  ),
                )),
          ),
        ],
      ),
    ),
  );
}
