import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import '../template/app_text_style.dart';
import '../template/border_radius.dart';
import '../template/spaceVH.dart';
import 'component/buttons/PrimaryButton.dart';

Widget ItemSetting(
    {required String title, required IconData icon, required String route}) {
  return ElevatedButton(
      style: ElevatedButton.styleFrom(
        padding: const EdgeInsets.all(15),
        shape: RoundedRectangleBorder(
          borderRadius: borderRadiusAll10(),
        ),
        elevation: 2,
      ),
      onPressed: () {
        Navigator.pushNamed(Get.context!, route);
      },
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Row(
            children: [
              dsButton(
                type: ButtonType.Icon,
                label: "",
                icon: icon,
              ),
              verticalSpaceSmall,
              Text(
                title,
                style: AppTypography.body1,
              )
            ],
          ),
          //dsButton(type: ButtonType.Icon, label: "",icon: Icons.arrow_forward_ios_rounded,)
        ],
      ));
}
