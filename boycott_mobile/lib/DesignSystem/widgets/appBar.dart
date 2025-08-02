import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../template/app_colors.dart';
import '../template/app_text_style.dart';

Widget dsAppBar(BuildContext context, String title){

  return AppBar(
    elevation: 0,
    title:  Text(title,style: AppTypography.headline1.copyWith(color:AppColors.WHITE_COLOR),),
    centerTitle: true,
    leading: IconButton(
      icon: const Icon(Icons.arrow_back_ios,color: Colors.white,),
      onPressed: () {
        Navigator.pop(context);
      },
    ),
  );
}
