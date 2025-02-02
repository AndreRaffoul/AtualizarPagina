import { Component } from '@angular/core';
import { CONTENTComponent } from '../HOME/content/content.component';
import { FOOTERComponent } from '../HOME/footer/footer.component';
import { MENUComponent } from '../HOME/menu/menu.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MENUComponent, CONTENTComponent, FOOTERComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HOMEComponent {

}
