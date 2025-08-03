import 'package:boycott/DesignSystem/template/app_colors.dart';
import 'package:flutter/material.dart';

class listRecommendationByCategories extends StatelessWidget {
  const listRecommendationByCategories({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Recommendations'),
        centerTitle: true,
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: const [
          _ProductListItem(
            imageUrl: 'assets/images/fake/ice.png',
            title: 'ICE',
            subtitle: 'Maroc',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/pepsi.png',
            title: 'Pepsi',
            subtitle: 'Coca',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/ice.png',
            title: 'ICE',
            subtitle: 'Maroc',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/pepsi.png',
            title: 'Pepsi',
            subtitle: 'Coca',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/pepsi.png',
            title: 'Pepsi',
            subtitle: 'Coca',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/ice.png',
            title: 'ICE',
            subtitle: 'Maroc',
            isGood: true,
          ),
          _ProductListItem(
            imageUrl: 'assets/images/fake/pepsi.png',
            title: 'Pepsi',
            subtitle: 'Coca',
            isGood: true,
          ),
        ],
      ),
    );
  }
}

class _ProductListItem extends StatelessWidget {
  final String imageUrl;
  final String title;
  final String? subtitle;
  final bool isGood;

  const _ProductListItem({
    required this.imageUrl,
    required this.title,
    this.subtitle,
    required this.isGood,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      elevation: 2,
      color: AppColors.WHITE_COLOR,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 14),
        child: Row(
          children: [
            // Product Image
            Container(
              width: 100,
              height: 100,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(8),
              ),
              child: _buildImage(imageUrl),
            ),
            const SizedBox(width: 16),
            // Product Info
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 16,
                    ),
                  ),
                  if (subtitle != null)
                    Text(
                      subtitle!,
                      style: TextStyle(
                        fontSize: 14,
                        color: Colors.grey[600],
                      ),
                    ),
                ],
              ),
            ),
            const SizedBox(width: 16),
            // Status Indicator
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 6),
              decoration: BoxDecoration(
                color: isGood ? Colors.green[50] : Colors.red[50],
                borderRadius: BorderRadius.circular(20),
              ),
              child: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Icon(
                    isGood ? Icons.check_circle : Icons.cancel,
                    color: isGood ? Colors.green : Colors.red,
                    size: 18,
                  ),
                  const SizedBox(width: 4),
                  Text(
                    isGood ? 'Safe' : 'Boycott',
                    style: TextStyle(
                      color: isGood ? Colors.green : Colors.red,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(width: 16),
          ],
        ),
      ),
    );
  }

  Widget _buildImage(String path) {
    if (path.startsWith('http')) {
      return Image.network(
        path,
        fit: BoxFit.cover,
      );
    } else {
      return Image.asset(
        path,
        fit: BoxFit.cover,
      );
    }
  }
}
