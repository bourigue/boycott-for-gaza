import 'package:flutter/material.dart';

import '../../../DesignSystem/template/app_colors.dart';
import 'list_recommendation_by_categories.dart';

class BoycottRecommendationsPage extends StatelessWidget {
  const BoycottRecommendationsPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Recommendations'),
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            // NEW Comparison Card
            _buildComparisonCard(context),
            _buildComparisonCard(context),
            _buildComparisonCard(context),
          ],
        ),
      ),
    );
  }

  Widget _buildComparisonCard(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        _buildMiniCard(
            imageUrl: 'assets/images/fake/ice.png',
            title: 'ICE',
            subtitle: 'Maroc',
            isGood: false,
            context: context),
        const Padding(
          padding: EdgeInsets.symmetric(horizontal: 8),
          child: Icon(
            Icons.compare_arrows,
            size: 30,
            color: AppColors.PRIMARY_COLOR,
          ),
        ),
        _buildMiniCard(
            imageUrl: 'assets/images/fake/pepsi.png',
            title: 'Pepsi',
            subtitle: 'Coca',
            isGood: true,
            context: context),
      ],
    );
  }

  Widget _buildMiniCard(
      {required String imageUrl,
      required String title,
      String? subtitle,
      required bool isGood,
      required BuildContext context}) {
    return Expanded(
      child: Card(
        color: Colors.white,
        elevation: 3,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
        child: InkWell(
          onTap: () => {
            // Navigate to this page
            Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => const listRecommendationByCategories()),
            )
          },
          child: Padding(
            padding: const EdgeInsets.all(12),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Align(
                  alignment: Alignment.topRight,
                  child: Icon(
                    isGood ? Icons.check_circle : Icons.cancel,
                    color: isGood ? Colors.green : Colors.red,
                  ),
                ),
                const SizedBox(height: 8),

                // Flexible Image
                Flexible(
                  fit: FlexFit.loose,
                  child: _buildImage(imageUrl),
                ),

                const SizedBox(height: 12),

                // Title
                Text(
                  title,
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 14,
                  ),
                ),

                // Optional Subtitle
                if (subtitle != null)
                  Text(
                    subtitle,
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 13,
                      color: Colors.grey[600],
                    ),
                  ),

                const SizedBox(height: 12),

                // Quality Label
                Text(
                  isGood ? 'Excellent' : 'Bad',
                  style: TextStyle(
                    color: isGood ? Colors.green : Colors.red,
                    fontWeight: FontWeight.w500,
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildImage(String path) {
    if (path.startsWith('http') || path.startsWith('https')) {
      return Image.network(
        path,
        fit: BoxFit.contain,
        height: 100,
      );
    } else {
      return Image.asset(
        path,
        fit: BoxFit.contain,
        height: 100,
      );
    }
  }
}
