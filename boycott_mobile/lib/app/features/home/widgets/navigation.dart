// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables, import_of_legacy_library_into_null_safe, prefer_final_fields, library_private_types_in_public_api

import 'package:flutter/material.dart';
import 'package:google_nav_bar/google_nav_bar.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import '../../../../DesignSystem/template/app_colors.dart';
import '../../product/add.dart';
import '../../product/add_product.dart';
import '../../recommendations/boycott_recommendations.dart';
import '../home.dart';

class MyNavigationBar extends StatefulWidget {
  const MyNavigationBar({Key? key}) : super(key: key);

  @override
  _MyNavigationBarState createState() => _MyNavigationBarState();
}

class _MyNavigationBarState extends State<MyNavigationBar> {
  int _selectedIndex = 0;
  //User? user = FirebaseAuth.instance.currentUser;
  static const TextStyle optionStyle =
      TextStyle(fontSize: 30, fontWeight: FontWeight.w600);
  static List<Widget> _widgetOptions = <Widget>[
    HomePage(),
    BoycottRecommendationsPage(),
    HomePage(),
    HomePage(),
    CreationHomePage(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.PRIMARY_COLOR,
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          print("BOYCOTT ACTION TRIGGERED!");
          MobileScanner(
            controller: MobileScannerController(
              detectionSpeed: DetectionSpeed.normal,
              facing: CameraFacing.back,
            ),
            onDetect: (capture) {
              final barcode = capture.barcodes.first;
              if (barcode.rawValue == null) {
                print('Failed to scan');
              } else {
                print('Scanned: ${barcode.rawValue}');
              }
            },
            errorBuilder: (context, error, child) {
              return Text('Camera Error: $error');
            },
          );
        },
        backgroundColor: AppColors.PRIMARY_COLOR, // Red for urgency
        foregroundColor: Colors.white,
        elevation: 10,
        child: Icon(
          Icons.document_scanner, // Symbolizes resistance/taking action
          size: 30,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      bottomNavigationBar: Container(
        decoration: BoxDecoration(
          color: AppColors.WHITE_COLOR,
          boxShadow: [
            BoxShadow(
              blurRadius: 20,
              color: Colors.black.withOpacity(.1),
            )
          ],
        ),
        child: SafeArea(
          child: (true)
              ? Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 15.0, vertical: 8),
                  child: GNav(
                    rippleColor: Colors.grey[300]!,
                    hoverColor: Colors.grey[100]!,
                    gap: 8,
                    activeColor: Colors.white,
                    iconSize: 24,
                    padding: EdgeInsets.symmetric(horizontal: 20, vertical: 12),
                    duration: Duration(milliseconds: 400),
                    tabBackgroundColor: AppColors.PRIMARY_COLOR,
                    color: AppColors.PRIMARY_COLOR_DARK,
                    tabs: [
                      GButton(
                        icon: Icons.history,
                        text: 'History',
                      ),
                      GButton(
                        icon: Icons.change_circle_outlined,
                        text: 'Recs',
                      ),
                      GButton(
                        icon: Icons.dashboard_outlined,
                        text: 'Overview',
                      ),
                      GButton(
                        icon: Icons.search_rounded,
                        text: 'Search',
                      ),
                      GButton(
                        icon: Icons.add_circle_outline_sharp,
                        text: 'Add',
                      ),
                    ],
                    selectedIndex: _selectedIndex,
                    onTabChange: (index) {
                      setState(() {
                        _selectedIndex = index;
                      });
                    },
                  ),
                )
              : Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 0, vertical: 0)),
        ),
      ),
    );
  }
}
