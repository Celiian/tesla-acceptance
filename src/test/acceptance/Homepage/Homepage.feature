Feature: Fonctionnalités de ma page d'accueil
	Scenario: Vérification du titre et de la description
		Given je suis sur la homepage "https://www.tesla.com/fr_FR/"
		Then le titre doit être "Véhicules électriques, énergie solaire et propre | Tesla"
		And la description doit être "Tesla accélère la transition mondiale vers une énergie durable en proposant des véhicules électriques, des panneaux solaires et des solutions intégrées d'énergie renouvelable pour les particuliers et les entreprises."
		And il existe cinq titres importants qui doivent être "-Model Y-Model 3-Model S-Model X-Systèmes d'énergie solaire et Powerwalls"

	Scenario: Vérification des liens du menu
		Given je suis sur la homepage "https://www.tesla.com/fr_FR/"
		Then les liens du menu mènent vers ces liens "https://www.tesla.com/fr_fr/models-https://www.tesla.com/fr_fr/model3-https://www.tesla.com/fr_fr/modelx-https://www.tesla.com/fr_fr/modely-https://www.tesla.com/fr_fr/powerwall-https://www.tesla.com/fr_fr/charging"
		Then dans le burger menu de droite ces éléments sont des liens "Véhicules Disponibles-Véhicules D'occasion-Reprise-Essais-Véhicules de société-Cybertruck-Roadster-Électricité pour les professionnels-Industries-Énergie-Nous trouver-Événements-Assistance"
