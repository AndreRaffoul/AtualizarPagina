import { Component } from '@angular/core';
import { MENUComponent } from "./menu/menu.component";
import { CONTENTComponent } from "./content/content.component";
import { FOOTERComponent } from "./footer/footer.component";

@Component({
  selector: 'app-home',
  imports: [MENUComponent, CONTENTComponent, FOOTERComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HOMEComponent {

}
