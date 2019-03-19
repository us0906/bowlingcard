<template>
<div class="game">
    <h4>Bitte gib dein Bowling-Ergebnis an - das sind die Pins, die du umgeworfen hast. </h4>

    <template v-if="response.activeFrame < 10" v-for="(n, index) in 11">
        <button v-if="((10-response.activeFrameScore)>=index) || (response.activeFrame == 9)" @click="bowl(index)"> {{ index }}</button>
        <button v-else @click="bowl(index)" disabled> {{ index }}</button>
        <span>&nbsp;</span>
    </template>
    <span>&nbsp;</span>
    <button @click="clear()">Clear</button>
    <p>
    <h4>Dein Score lautet:</h4>
    <div>
      <table style="width:75%" border="8">
        <tr>
            <th>Frame</th>
            <th v-for="(n, index) in 10"width="10%">{{index+1}}</th>
        </tr>
        <tr>
            <td>
                <table>
                    <tr><td>Pins</td></tr>
                    <tr><td>Score</td></tr>
                </table>
            </td>
            <td v-for="(item,index) in response.frames">
                <table border="0" width="100%">
                    <tr>
                        <template v-if="index < 9">
                            <template v-if="item.strike">
                                <td width="50%"></td>
                                <td width="50%">X</td>
                            </template>
                            <template v-else-if="item.spare">
                                <td width="50%">{{ item.scores[0] }}</td>
                                <td width="50%">/</td>
                            </template>
                            <template v-else>
                                <td>{{ item.scores[0] }}</td>
                                <td>{{ item.scores[1] }}</td>
                            </template>
                        </template>
                        <template v-else>
                            <td v-if="item.scores[0] === 10">X</td>
                            <td v-else>{{ item.scores[0] }}</td>
                            <td v-if="item.scores[1] === 10">X</td>
                            <td v-else>{{ item.scores[1] }}</td>
                            <td v-if="item.scores[2] === 10">X</td>
                            <td v-else>{{ item.scores[2] }}</td>
                        </template>
                    </tr>
                </table>
                <table border="0" width="100%">
                    <td> <b>{{ item.score}}</b></td>
                </table>
            </td>
        </tr>
       </table>
    </div>
</div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'game',

    data () {
      return {
          pins: [],
          response:  { frames: [
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                  { scores: [ 0, 0, 0 ], score: 0, strike: false, spare: false }
                  ],
              activeFrame: 0, activeFrameScore: 0, total: 0 },
          errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
        clear() {
            this.pins = [];
            this.response = { frames: [
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0 ], score: 0, strike: false, spare: false },
                    { scores: [ 0, 0, 0 ], score: 0, strike: false, spare: false }
                ],
                activeFrame: 0, activeFrameScore: 0, total: 0 };
            this.pinsInFrame = 0;
        },
        bowl(numberOfPins) {
            this.pins.push (numberOfPins);
            this.response = this.pins.join(', ');
            let url = '/play?pins='+this.pins.join ('&pins=');
            console.log ('url: '+url);
            AXIOS.get(url)
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data;
                    console.log(response.data);

                })
                .catch(e => {
                    this.errors.push(e)
                })
        }
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
