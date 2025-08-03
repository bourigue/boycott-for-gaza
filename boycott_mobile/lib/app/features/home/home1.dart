import 'package:boycott/app/features/home/widgets/appbar.dart';
import 'package:flutter/material.dart';
import '../../../DesignSystem/template/app_colors.dart';

class HomePage extends StatefulWidget {
  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String name = "";

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: SingleChildScrollView(
          child: Container(
            decoration: BoxDecoration(
              color: AppColors.PRIMARY_COLOR,
            ),
            child: Column(
              children: [
                // ****AppBar start****
                MyAppBar(),

                const SizedBox(
                  height: 36,
                ),
                // ****AppBar End****
                Container(
                  width: MediaQuery.of(context).size.width,
                  // height: MediaQuery.of(context).size.height,
                  padding: const EdgeInsets.all(15),
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(25),
                      topRight: Radius.circular(25),
                    ),
                    color: AppColors.WHITE_COLOR,
                  ),
                  // ***filters***
                  child: Column(
                    children: [],
                  ),

                  // *** end filters
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
