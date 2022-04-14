Feature: Evenements tesla
  Scenario:Accéder a la page et voir des évenements
    Given Je suis sur la page d'accueil "https://www.tesla.com/fr_fr" en passant par le burger menu je peut cliquer sur le bouton évènement qui m'amène sur cette page "https://www.tesla.com/fr_FR/events"
    Then La page contient cinq Evenements différents
    Then Lorsque je cherche un événement à Londres, Royaume-Uni, le premier résultat de recherche indique un événement localisé à "Royaume-Uni"
    Then Je souhaite m'inscrire à un événement qui aura lieu en France,je fais une recherche en France puis je clic sur le lien Informations d'un événement. Je suis alors redirigé vers la page "https://www.tesla.com/fr_FR/event/paris-test-drive-event"

