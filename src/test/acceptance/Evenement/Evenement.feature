Feature: Evenements tesla
  Scenario:Accéder a la page et voir des évenements puis s'inscire
    Given Je suis sur la page d'accueil "https://www.tesla.com/fr_fr" en passant par le burger menu je peut cliquer sur le bouton "Événements" qui m'amène sur cette page "https://www.tesla.com/fr_fr/events"
    Then La page contient "15" Evenements différents
    Then Lorsque je cherche un événement à "Londres" le premier résultat de recherche indique un événement localisé à "Royaume-Uni"
    Then Je souhaite m'inscrire à un événement qui aura lieu en France,je fais une recherche pour la "France" puis je clic sur le lien Informations d'un événement. Je suis alors redirigé vers la page "https://www.tesla.com/fr_FR/event/paris-test-drive-event" de l'événement "Test Drive Event"
    Then Un formulaire d'inscription est disponible avec les champs "Prénom" "Nom" "E-mail" "Téléphone" "Région" "Code postal" "Recevoir les News Tesla" et un bouton d'envoi "Suivant" Lorsque je remplis le formulaire sans l'email et que je le valide un message rouge apparait sous le champ de l'email indiquant "obligatoire"
