import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FOOTERComponent } from "./componentes/HOME/footer/footer.component";
import { MENUComponent } from "./componentes/HOME/menu/menu.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MENUComponent, FOOTERComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AeLModas';
}
