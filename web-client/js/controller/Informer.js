/**
 * This class provides methods to inform the user.
 * @author Nizovkin_A.V.
 * @copyright 2026 Nizovkin_A.V.
 */
export class Informer {
    
    /**
     * Displays an information panel  with message
     * @param {string} message 
     */
    static showMessage(message) {
        const panelInfo = document.getElementById("survey-panel-info");
        const overlay = document.getElementById("overlay");
        document.getElementById("survey-info-message").innerHTML = message;

        panelInfo.classList.toggle("open");
        overlay.classList.toggle("open");
    }

}