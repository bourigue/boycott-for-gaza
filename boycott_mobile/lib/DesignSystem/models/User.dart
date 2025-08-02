class User {
  final int id;
  final String email;
  final String nom;
  final String role;
  final String avatar;

  User({
    required this.id,
    required this.email,
    required this.nom,
    required this.role,
    required this.avatar,
  });

  // Factory constructor to create a User from a JSON map
  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      email: json['email'],
      nom: json['nom'],
      role: json['role'],
      avatar: json['avatar'],
    );
  }

  // Method to convert User to JSON (if needed later)
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'email': email,
      'nom': nom,
      'role': role,
      'avatar': avatar,
    };
  }
}
