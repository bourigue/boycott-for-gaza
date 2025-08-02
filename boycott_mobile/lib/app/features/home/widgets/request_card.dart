// ignore_for_file: non_constant_identifier_names, file_names, prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../../../DesignSystem/template/app_colors.dart';
import 'scrollImage.dart';
Widget requestCard(String name, int age, String budget, String localisation,
    int capacity, String profile, String imgpost, String idpost,String addresse) {
  return InkWell(
      onTap: () async {


      child: Container(

        margin: EdgeInsets.only(bottom: 15),
        padding: EdgeInsets.all(10),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(16)),
          color: AppColors.WHITE_COLOR,
        ),
        child: Expanded(
          child: Column(
            children: [
              SizedBox(
                height: 150,
                child: scrollImage(imgpost),
              ),
              Container(
                margin: EdgeInsets.only(top: 10),
                child: ListTile(
                  title: Text(name,
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: AppColors.BLACK_COLOR)),
                  subtitle: Text(
                    "$age years old",
                  ),
                  trailing: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: AppColors.RED_COLOR.withOpacity(.1),
                      shadowColor: Colors.transparent,
                      shape: StadiumBorder(),
                    ),
                    onPressed: () async {

                    },
                    child: Icon(Icons.location_on_outlined,
                        color: AppColors.RED_COLOR),
                  ),
                  leading: Stack(children: [
                    CircleAvatar(
                      backgroundColor: Color(0xff00A3FF),
                      backgroundImage: NetworkImage(profile),
                      radius: 25.0,
                    ),
                  ]),
                ),
              ),
              SizedBox(
                height: 10,
              ),
              Container(
                padding: EdgeInsets.all(10),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text("$capacity personnes",
                        style: TextStyle(
                            fontWeight: FontWeight.bold,
                            color: AppColors.BLACK_COLOR)),
                    Text("$budget DH/ month",
                        style: TextStyle(
                            fontWeight: FontWeight.bold,
                            color: AppColors.BLACK_COLOR))
                  ],
                ),
              )
            ],
          ),
),
      );

});
}
