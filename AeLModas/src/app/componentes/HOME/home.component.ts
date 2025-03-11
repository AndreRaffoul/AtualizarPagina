import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CONTENTComponent } from "./content/content.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule, RouterModule,
    CONTENTComponent
],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HOMEComponent {

  imagemLogo: string = 'assets/imagens/logo.png';
  imagemBanner: string = 'banner.jpg';

}
