// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, non_constant_identifier_names

import 'package:flutter/material.dart';

import '../../../../DesignSystem/resources/app_constants.dart';
import '../../../../DesignSystem/template/app_colors.dart';

class MyAppBar extends StatefulWidget {
  const MyAppBar({Key? key}) : super(key: key);

  @override
  State<MyAppBar> createState() => _MyAppBarState();
}

class _MyAppBarState extends State<MyAppBar> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Padding(
          padding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 20.0),
          child: Stack(
            children: <Widget>[
              Container(
                height: 50,
                width: 50,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(16),
                    border: Border.all(color: AppColors.WHITE_COLOR, width: 4),
                    image: DecorationImage(
                        image: NetworkImage("profile"), //user profile
                        fit: BoxFit.cover)),
              ),
              Align(
                alignment: Alignment.center,
                child: Column(
                  children: const [
                    Text(
                      AppConstants.appName,
                      style: TextStyle(
                        color: AppColors.WHITE_COLOR,
                        fontSize: 25,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    SizedBox(
                      height: 7,
                    ),
                    Text(
                      "",
                      style: TextStyle(color: AppColors.WHITE_COLOR),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
