import { Component } from '@angular/core';
import { HOMEComponent } from "./componentes/HOME/home.component";

@Component({
  selector: 'app-root',
  imports: [HOMEComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AeLModas-frontend';
}
