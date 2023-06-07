/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package managementsystempackage.model;

/**
 *
 * @author renza
 */
public interface IEventListener <E> {
    public void  onClickEvent( E data);
    public void onClickDelete( E data);

}
