Feature: caractéristiques de la Tesla Modèle 3
  Scenario Outline: Caractéristiques complètes de la model3
    Given Je suis sur la page "https://www.tesla.com/fr_fr/model3"
    Then Je veut vérifier que les caractéristiques "<Gamme>" "<Poids>" "<Accélération>" et "<Autonomie>" sont présentes



    Examples:
      | Gamme | Poids | Accélération | Autonomie |
      | Performance | 1,836 kg | 3,3 secondes | 547 km |
      | Grande Automonie AWD | 1,830 kg | 4,4 secondes | 602 km |
      | Propulsion | 1,752 kg | 6,1 secondes | 491 km |