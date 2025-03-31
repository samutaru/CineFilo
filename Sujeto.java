/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajois2;


interface Sujeto {
    void registerObserver (Observador o);
    void removeObserver(Observador o);
    void notifyObservers();
}
