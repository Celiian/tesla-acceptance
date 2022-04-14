Feature: Configurateur Model S (https://www.tesla.com/fr_fr/model3)
  Scenario: Pouvoir commander une voiture configuré
    Given Je suis sur la page "https://www.tesla.com/fr_fr/model3"
    Then Quand je clique sur le bouton commander je doit arriver sur la page "https://www.tesla.com/fr_fr/model3/design"
    And Pour connaitre la liste des localisations de vente je vais en bas de la page puis je clique clique sur le bouton Locations qui doit m'amener sur la page "https://www.tesla.com/findus/list"

    Scenario: Pouvoir configurer une Tesla model 3 simple
      Given Je suis sur la page "https://www.tesla.com/fr_fr/model3/design"
      Then Par défaut le prix affiché est un prix "LOA" à "386,91 € /mois"
      Then Quand je sélectionne l'option Capacité de conduite entièrement autonome, le prix augmente de "88.99"

      Scenario Outline: Pouvoir choisir une Tesla model 3 différente
        Given Je suis sur la page "https://www.tesla.com/fr_fr/model3/design" et je sélectionne le modèle "<model>"
        Then Le prix en LOA passe à "<prix-loa>"
        Then L'économie de carburant estimé passe à "<économie>"
        Then Le montant total avec achat au terme du contrat de "<total>"

        Examples:
          | model | prix-loa | économie | total |
          | Model 3 Performance | 541,16 € | 175 | 71 426 |
          | Model 3 Grande Autonomie | 493,70 € | 175 | 66 906 |

