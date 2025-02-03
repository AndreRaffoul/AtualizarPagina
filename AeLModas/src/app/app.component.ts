import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HOMEComponent } from "./componentes/HOME/home.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HOMEComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'AeLModas';
}
